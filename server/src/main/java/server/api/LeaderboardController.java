package server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.database.LeaderboardRepository;
import commons.LeaderboardEntry;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardRepository repository;

    /**
     * Find the top 10 entries in the leaderboard according to the score
     * @return a list of leaderboard entries that have the highest 10 scores
     */
    @GetMapping("/getTop10")
    public List<LeaderboardEntry> getTop10() {
        return repository.findTop10ByOrderByScoreDesc();
    }

    /**
     * Save the given leaderboard entry in the leaderboard repository
     * @param leaderboardEntry
     * @return the leaderboard entry object that was saved to the repository
     */
    @PutMapping("/saveScore")
    @ResponseBody
    public LeaderboardEntry saveScore(@RequestBody LeaderboardEntry leaderboardEntry) {
        repository.save(leaderboardEntry);
        return leaderboardEntry;
    }

}
