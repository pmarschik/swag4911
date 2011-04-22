package swag.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {

	@Id
	@GeneratedValue
	public UUID id;
	
	@Column(nullable = false)
	public Boolean online;
	
	@Column(nullable = false)
	public Boolean deleted;
	
	public Player() {
	}
}
