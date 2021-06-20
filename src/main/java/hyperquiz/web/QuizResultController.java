package hyperquiz.web;

import com.fasterxml.jackson.annotation.JsonView;
import hyperquiz.exceptions.EntityUpdateException;
import hyperquiz.model.QuizResult;
import hyperquiz.services.QuizResultService;
import hyperquiz.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="hyperquiz/quizresults")
public class QuizResultController {

    @Autowired
    QuizResultService qrs;

    @GetMapping
    @JsonView(View.QuizResultView.External.class)
    public List<QuizResult> getAllQuizResults(){
        return qrs.getAllQuizResults();
    }

    @GetMapping("/{id}")
    @JsonView(View.QuizResultView.External.class)
    public QuizResult getQuizResultByID(@PathVariable Long id){
      return  qrs.findQuizResultByID(id);
    }

    @DeleteMapping("/{id}")
    @JsonView(View.QuizResultView.External.class)
    public QuizResult deleteQuizResultByID(@PathVariable Long id){
        return qrs.deleteQuizResultByID(id);
    }

    @PostMapping
    public ResponseEntity<QuizResult> createQuizResult(@Valid @RequestBody QuizResult qr){
            QuizResult quizResult = qrs.addQuizResult(qr);
            return ResponseEntity.created(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .pathSegment("{id}")
                            .buildAndExpand(quizResult.getId())
                            .toUri() ).body(quizResult);
    }

    @PutMapping("/{id}")
    public QuizResult updateQuizResult(@Valid @RequestBody QuizResult qr,@PathVariable Long id){
        if(!qr.getId().equals(id)){
            throw new EntityUpdateException("IDs passed are different: "+qr.getId()+" != "+id);
        }
        return qrs.updateQuizResult(qr);
    }




}
