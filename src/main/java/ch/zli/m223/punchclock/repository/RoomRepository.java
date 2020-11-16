package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
