package com.adm.journalApp.repository;

import java.util.List;
import org.bson.types.ObjectId;
import com.adm.journalApp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.adm.journalApp.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId>{
    List<JournalEntry> findByUserId(ObjectId userId);
} 
