package dev.luanfernandes.meetingroom.controller;

import dev.luanfernandes.meetingroom.model.Room;
import dev.luanfernandes.meetingroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {

    RoomService roomService;

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") long roomId) {

        Room room = roomService.findById(roomId);
        return ResponseEntity.ok().body(room);

    }

    @PostMapping("rooms")
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomService.saveMeeting(room);
    }

    @PutMapping("rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value = "id") long roomId,
                                           @Valid @RequestBody Room roomDetails) {
        Room updateRoom = roomService.updateMeeting(roomId,roomDetails);
        return ResponseEntity.ok(updateRoom);
    }

    @DeleteMapping("rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId){
        return roomService.deleteMeeting(roomId);
    }
}
