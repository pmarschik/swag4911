package swag.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
	@Id
	private UUID id;
}
