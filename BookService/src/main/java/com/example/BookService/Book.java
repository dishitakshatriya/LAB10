/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BookService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author dishi
 */
@Data
@Entity
public class Book {
    private String name;
    private String author;
    private String genre;
    
    @GeneratedValue
    @Id
    private long id;
}
