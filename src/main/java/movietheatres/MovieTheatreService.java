package movietheatres;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;

public class MovieTheatreService {
    private Map<String, Set<Movie>> shows=new LinkedHashMap<>();
    public void readFromFile(Path path) {
        try(BufferedReader reader= Files.newBufferedReader(path)){
            String line;
            while((line=reader.readLine())!=null){
                process(line);
            }
        }
        catch(IOException ioe){
            throw new IllegalArgumentException("");
        }

    }

    private void process(String line) {
        String[] parts=line.split("-");
        String theatreName=parts[0];
        String[] movieParts=parts[1].split(";");
        Movie movie=new Movie(movieParts[0], LocalTime.parse(movieParts[1]));
        insert(theatreName,movie);
    }

    private void insert(String theatreName, Movie movie) {
        if(!shows.containsKey(theatreName)){
            shows.put(theatreName,new TreeSet<>(Comparator.comparing(Movie::getStartTime)));
        }
        shows.get(theatreName).add(movie);
    }

    public Map<String, Set<Movie>> getShows() {
        return shows;
    }

    public List<String> findMovie(String title) {
        return shows.entrySet().stream()
                .filter(e->e.getValue().stream().anyMatch(m->m.getTitle().equals(title)))
                .map(e->e.getKey())
                .toList();
    }

    public LocalTime findLatestShow(String title) {
        return shows.entrySet().stream().flatMap(e->e.getValue().stream()).filter(m->m.getTitle().equals(title)).map(Movie::getStartTime).max(Comparator.naturalOrder()).orElseThrow(() -> new IllegalArgumentException(""));
    }
}
