package pl.brzezinski.noteit.api;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.brzezinski.noteit.api.viewModel.FeedbackViewModel;
import pl.brzezinski.noteit.mail.FeedbackSender;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedbackController {

    private FeedbackSender feedbackSender;

    public FeedbackController(FeedbackSender feedbackSender) {
        this.feedbackSender = feedbackSender;
    }

    @PostMapping
    public void sendFeedback(@RequestBody FeedbackViewModel feedbackViewModel, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Feedback has errors; Can not send feedback");
        }

        this.feedbackSender.sendFeedback(
                feedbackViewModel.getEmail(),
                feedbackViewModel.getName(),
                feedbackViewModel.getFeedback());
    }
}
