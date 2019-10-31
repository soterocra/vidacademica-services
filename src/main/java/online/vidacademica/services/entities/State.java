package online.vidacademica.services.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_state")
public class State implements Serializable {
    private static final long serialVersionUID = 2117548390130629662L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "country_id")
    private Country country;
    
    @OneToMany(mappedBy = "state")
    private Set<City> cities = new HashSet<>();
    
    public State() {
    }

    public State(Long id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
	public Set<City> getCities() {
		return cities;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return id.equals(state.id);
    }
    
    public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
