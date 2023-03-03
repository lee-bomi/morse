package com.zerobase.morse.repository;

import com.zerobase.morse.entity.ChatContent;
import com.zerobase.morse.entity.ChatRoom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatContentRepository extends JpaRepository<ChatContent, Integer> {

  List<ChatContent> findByChatRoomOrderByWriteDt(ChatRoom roomId);

}
