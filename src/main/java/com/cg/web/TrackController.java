package com.cg.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cg.entity.Track;
import com.cg.repo.TrackRepo;

@RestController
@RequestMapping("/music/tracks")
public class TrackController {

    @Autowired
    private TrackRepo repo;
    @PostMapping
    public ResponseEntity<String> addTrack(@RequestBody Track track) {
        repo.save(track);
        return new ResponseEntity<>("Track Added", HttpStatus.CREATED);
    }

    // ✅ GET - All Tracks
    @GetMapping()
    public ResponseEntity<List<Track>> getTracks() {
        List<Track> list = repo.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Track>> getTracksByTitle(@RequestParam String title) {
        List<Track> list = repo.findByTitle(title);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTrack(@PathVariable Long id) {
        Optional<Track> track = repo.findById(id);

        if (track.isPresent()) {
            return new ResponseEntity<>(track.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Track Not Found", HttpStatus.NOT_FOUND);
        }
    }
}