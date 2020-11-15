package ch.zli.m223.punchclock.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import ch.zli.m223.punchclock.domain.Room;
import ch.zli.m223.punchclock.service.RoomService;
import ch.zli.m223.punchclock.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private RoomService roomService;
    private UserDetailsServiceImpl userService;

    public RoomController(RoomService roomservice, UserDetailsServiceImpl userservice){
        this.roomService = roomservice;
        this.userService = userservice;

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable long id) {
        
        Optional<Room> room = roomService.findById(id);
        if (room.isEmpty()) return;
        roomService.deleteRoom(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Room updateRoom(@PathVariable long id, @Valid @RequestBody Room room) {

        Optional<Room> editroom = roomService.findById(id);
        if (editroom.isEmpty()) return null;
        room.setId(id);
        return roomService.updateRoom(room);
    }
    
}
