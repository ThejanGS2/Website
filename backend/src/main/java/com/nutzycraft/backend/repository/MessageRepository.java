package com.nutzycraft.backend.repository;

import com.nutzycraft.backend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Find chat history between two users directly
    @Query("SELECT m FROM Message m WHERE (m.sender.id = :u1 AND m.receiver.id = :u2) OR (m.sender.id = :u2 AND m.receiver.id = :u1) ORDER BY m.timestamp ASC")
    List<Message> findChatHistory(Long u1, Long u2);

    // Find all messages involving a user (for inbox list)
    @Query("SELECT m FROM Message m WHERE m.sender.id = :userId OR m.receiver.id = :userId ORDER BY m.timestamp DESC")
    List<Message> findUserMessages(Long userId);

    // Find unread count
    long countByReceiver_IdAndIsReadFalse(Long userId);
}
