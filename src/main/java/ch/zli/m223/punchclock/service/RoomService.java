package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Room;
import ch.zli.m223.punchclock.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Entry createRoom(Room room) {
        return roomRepository.saveAndFlush(room);
    }

    public List<Entry> findAll() {
        return roomRepository.findAll();
    }

    public Entry findById(long id){
        return roomRepository.findById(id);
    }

    public void deleteRoom(long id){
        roomRepository.deleteById(id);
    }

    public Entry updateRoom(Room room){
        return roomRepository.save(room);
    }
}
