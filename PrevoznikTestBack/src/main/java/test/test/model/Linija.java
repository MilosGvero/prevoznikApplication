package test.test.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "linije")
public class Linija {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column(nullable = false)
	 private int brojMesta;
	 
	 @Column
	 private double cenaKarte;
	 
	 @Column
	 private String polazak;
	 
	 @Column(nullable = false)
	 private String destinacija;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(nullable = false)
	 private Prevoznik prevoznik;
	 
	 public void smanjiBrojMesta() {
	        if (brojMesta > 0) {
	            brojMesta--;
	        } else {
	            throw new RuntimeException("Nema dovoljno mesta za izabranu liniju.");
	        }
	    }

	public Linija(Long id, int brojMesta, double cenaKarte, String polazak, String destinacija, Prevoznik prevoznik) {
		super();
		this.id = id;
		this.brojMesta = brojMesta;
		this.cenaKarte = cenaKarte;
		this.polazak = polazak;
		this.destinacija = destinacija;
		this.prevoznik = prevoznik;
	}

	public Linija() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public String getPolazak() {
		return polazak;
	}

	public void setPolazak(String polazak) {
		this.polazak = polazak;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String deestinacija) {
		this.destinacija = deestinacija;
	}

	public Prevoznik getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
		if (prevoznik !=null && !prevoznik.getLinije().contains(this)) {
			prevoznik.getLinije().add(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linija other = (Linija) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Linija [id=" + id + ", brojMesta=" + brojMesta + ", cenaKarte=" + cenaKarte + ", polazak=" + polazak
				+ ", destinacija=" + destinacija + ", prevoznik=" + prevoznik.getNaziv() + "]";
	}
	 
	 
	 
	 
}
