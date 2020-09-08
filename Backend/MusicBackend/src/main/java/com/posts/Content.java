package com.posts;
import javax.persistence.*;


@Entity
@Table(name="POST_CONTENT")
public class Content 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name="path")
	private String fileLocation;
	
	@OneToOne(mappedBy="content")
	private Post post;
}
