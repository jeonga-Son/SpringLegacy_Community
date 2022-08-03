package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatMessageDto;
import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChatMessageRepository {
    private static List<ChatMessageDto> datum;
    private static long lastId;

    static {
        datum = new ArrayList<>();
        lastId = 0;

        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(roomId -> {
            IntStream.rangeClosed(1, 2).forEach(id -> {
            String body = "메세지 %d".formatted(id);
                write(roomId, body);
            });
        });
    }

    public static long write(long roomId, String body) {
        //나중에 사이트 데이터가 21억개가 넘을 것을 대비해서 long으로 잡음.
        long id = ++lastId;
        ChatMessageDto newChatMessageDto = new ChatMessageDto(id, roomId, body);

        datum.add(newChatMessageDto);

        return id;
    }

    public List<ChatMessageDto> findByRoomId(long roomId) {
        return datum
                .stream()
                .filter(chatMessageDto -> chatMessageDto.getRoomId() == roomId)
                .collect(Collectors.toList());
    }

    public List<ChatMessageDto> findByRoomIGreaterThan(long roomId, long fromId) {
        return datum
                .stream()
                .filter(chatMessageDto -> chatMessageDto.getRoomId() == roomId)
                .filter(chatMessageDto -> chatMessageDto.getId() > fromId)
                .collect(Collectors.toList());
    }
}


