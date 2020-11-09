package com.application.posts.files;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * File entity, contains the information for a file
 */
@Entity
@Table(name = "files")
public class FileDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    /**
     * This is a Lob(lump of bytes) that contains all the data of the file.
     */
    @Lob
    private byte[] data;

    /**
     * Default constructor
     */
    public FileDB() {
    }

    /**
     * Constructor that accepts all the fields for a file
     * @param name
     * @param type
     * @param data
     */
    public FileDB(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    /**
     *
     * @return the Id of the file
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return the name of the file
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the type of file(music, image, text, etc.)
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return the raw data of the file
     */
    public byte[] getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(byte[] data) {
        this.data = data;
    }

}
