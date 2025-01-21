package streams;


import java.time.LocalDate;
import java.util.*;

public class SongService {
    private List<Song> songs=new ArrayList<>();

    public void addSong(Song song){
        songs.add(song);
    }

    public Optional<Song> shortestSong() {
        return songs.stream().min(Comparator.comparing(Song::getLength));
    }

    public List<Song> findSongByTitle(String title) {
        return songs.stream().filter(s->title.equals(s.getTitle())).toList();
    }

    public boolean isPerformerInSong(Song song, String performer) {
        return song.getPerformers().stream().anyMatch(s->performer.equals(s));
    }

    public List<String> titlesBeforeDate(LocalDate date) {
        return songs.stream().filter(s->s.getRelease().isBefore(date)).map(Song::getTitle).toList();
    }
}
