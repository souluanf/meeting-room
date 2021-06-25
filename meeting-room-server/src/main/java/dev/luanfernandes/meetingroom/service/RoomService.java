package dev.luanfernandes.meetingroom.service;

import dev.luanfernandes.meetingroom.exceptions.ResourceNotFoundException;
import dev.luanfernandes.meetingroom.model.Room;
import dev.luanfernandes.meetingroom.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {

    RoomRepository roomRepository;

    @Autowired
    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findById(long roomId) {
        try {
            return roomRepository.findById(roomId).orElseThrow(
                    () -> new ResourceNotFoundException("Room not found::" + roomId)
            );
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Room not found::" + roomId);
        }
    }

    public Room saveMeeting(Room room) {
        return roomRepository.save(room);
    }

    public Room updateMeeting(long roomId, Room roomDetails) {
        try {
            Room room = roomRepository.findById(roomId).orElseThrow(
                    () -> new ResourceNotFoundException("Room not found::" + roomId)
            );
            room.setName(roomDetails.getName());
            room.setDate(roomDetails.getDate());
            room.setStartHour(roomDetails.getStartHour());
            room.setEndHour(roomDetails.getEndHour());
            return roomRepository.save(room);

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Room not found::" + roomId);
        }
    }

    public Map<String, Boolean> deleteMeeting(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new ResourceNotFoundException("Room not found for this ID::" + roomId)
        );
        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
