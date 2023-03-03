package com.zerobase.morse.repository;

import com.zerobase.morse.entity.ChatParticipant;
import com.zerobase.morse.entity.ChatRoom;
import com.zerobase.morse.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatParticipantRepository extends JpaRepository<ChatParticipant,Integer> {
  boolean existsByMemberAndChatRoom(Member email, ChatRoom roomId);
}
