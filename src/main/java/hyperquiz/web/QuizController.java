package hyperquiz.web;

import com.fasterxml.jackson.annotation.JsonView;
import hyperquiz.exceptions.EntityUpdateException;
import hyperquiz.model.Quiz;
import hyperquiz.services.QuizService;
import hyperquiz.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="hyperquiz/quizzes",produces = APPLICATION_JSON_VALUE)
public class QuizController {

    @Autowired
    QuizService qs;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @JsonView(View.QuizView.External.class)
    public List<Quiz> getAllQuizzes(){
        return qs.getAllQuizzes();
    }

    @GetMapping(path="/internal",produces = APPLICATION_JSON_VALUE)
    @JsonView(View.QuizView.Internal.class)
    public List<Quiz> getAllQuizzesInternal(){
        return qs.getAllQuizzes();
    }


    @GetMapping(path = "/{id}",produces = APPLICATION_JSON_VALUE)
    @JsonView(View.QuizView.External.class)
    public Quiz getQuizByID(@PathVariable Long id){
        return qs.findQuizByID(id);
    }
    
    @PostMapping(consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    @JsonView(View.QuizView.Internal.class)
    public ResponseEntity<Quiz> addQuiz(@Valid @RequestBody Quiz q){
        Quiz quiz = qs.addQuiz(q);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .pathSegment("{id}")
                        .buildAndExpand(quiz.getId())
                        .toUri() ).body(quiz);
    }

    @DeleteMapping(path="/{id}",produces = APPLICATION_JSON_VALUE)
    @JsonView(View.QuizView.External.class)
    public Quiz deleteQuizByID(@PathVariable Long id){
        return qs.deleteQuizByID(id);
    }

    @PutMapping(path="/{id}",consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    public Quiz updateQuiz(@Valid @RequestBody Quiz q, @PathVariable Long id){
        if(!id.equals(q.getId())){
            throw new EntityUpdateException("IDs passed are different: "+q.getId()+" != "+id);
        }
        return qs.updateQuiz(q);
    }

    @GetMapping(path="/titles/{title}",produces = APPLICATION_JSON_VALUE)
    @JsonView(View.QuizView.External.class)
    public Set<Quiz> findQuizByTitle(@PathVariable String title){
        return qs.findQuizByTitle(title);
    }

    @GetMapping(path="/tags/{tags}",produces = APPLICATION_JSON_VALUE)
    @JsonView(View.QuizView.External.class)
    public Set<Quiz> findQuizByTags(@PathVariable String tags){
        return qs.findQuizByTags(tags);
    }

    @GetMapping(path="/descriptions/{description}",produces = APPLICATION_JSON_VALUE)
    @JsonView(View.QuizView.External.class)
    public Set<Quiz>findQuizByDescription(@PathVariable String description){
        return qs.findQuizByDescription(description);
    }

    @GetMapping(path="/durations/{duration}",produces = APPLICATION_JSON_VALUE)
    @JsonView(View.QuizView.External.class)
    public Set<Quiz>findQuizByDuration(@PathVariable Integer duration){
        return qs.findQuizByDuration(duration);
    }

    @GetMapping(path="/criteria/{criteria}",produces = APPLICATION_JSON_VALUE)
    @JsonView(View.QuizView.External.class)
    public Set<Quiz>findQuizByWords(@PathVariable String criteria){
        return qs.findQuizzesByWords(criteria);
    }

}
