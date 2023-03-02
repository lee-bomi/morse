package com.zerobase.morse.repository;

import com.zerobase.morse.entity.ChatRoom;
import com.zerobase.morse.entity.Study;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {

  boolean existsByRoomId(int roomId);
  Optional<ChatRoom> findByStudyNoAndRoomType(Study studyNo, String roomType);
  ChatRoom getChatRoomByRoomId(int roomId);

}
