package com.kairova.simple.controller;

import com.kairova.simple.model.MentalHealthAssessment;
import com.kairova.simple.model.CareerAdvice;
import com.kairova.simple.model.CollaborationRequest;
import com.kairova.simple.model.FinancialAidApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
public class HomeController {
    
    // Mock data for career guidance
    private final Map<String, CareerAdvice> careerAdviceMap = new HashMap<>();
    
    // Store collaboration requests (in a real app, this would be a database)
    private final List<CollaborationRequest> collaborationRequests = new ArrayList<>();
    

    public HomeController() {
        // Initialize some sample career advice
        initializeCareerAdvice();
    }
    
    private void initializeCareerAdvice() {
        // Sample career advice data
        CareerAdvice programming = new CareerAdvice();
        programming.setInterest("Programming");
        programming.setCareerOptions(Arrays.asList("Software Developer", "Web Developer", "Mobile App Developer"));
        programming.setSkillsNeeded(Arrays.asList("Java", "Python", "JavaScript", "Data Structures"));
        programming.setResources(Arrays.asList("LeetCode", "HackerRank", "FreeCodeCamp"));
        
        CareerAdvice design = new CareerAdvice();
        design.setInterest("Design");
        design.setCareerOptions(Arrays.asList("UI/UX Designer", "Graphic Designer", "Product Designer"));
        design.setSkillsNeeded(Arrays.asList("Figma", "Adobe XD", "User Research", "Prototyping"));
        design.setResources(Arrays.asList("Behance", "Dribbble", "DesignCourse"));
        
        careerAdviceMap.put("programming", programming);
        careerAdviceMap.put("design", design);
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("assessment", new MentalHealthAssessment());
        model.addAttribute("collaborationRequest", new CollaborationRequest());
        return "index";
    }
    
    @GetMapping("/career")
    public String careerGuidance(Model model) {
        model.addAttribute("interests", careerAdviceMap.keySet());
        model.addAttribute("careerAdvice", new CareerAdvice());
        return "career";
    }
    
    @PostMapping("/career/advice")
    public String getCareerAdvice(@RequestParam String interest, Model model) {
        CareerAdvice advice = careerAdviceMap.get(interest.toLowerCase());
        model.addAttribute("careerAdvice", advice != null ? advice : new CareerAdvice());
        model.addAttribute("interests", careerAdviceMap.keySet());
        return "career";
    }
    
    @PostMapping("/collaborate")
    public String submitCollaboration(@ModelAttribute CollaborationRequest request, Model model) {
        collaborationRequests.add(request);
        model.addAttribute("collaborationRequest", new CollaborationRequest());
        model.addAttribute("successMessage", "Your collaboration request has been submitted successfully!");
        return "index";
    }
    
    @GetMapping("/collaborations")
    public String viewCollaborations(Model model) {
        model.addAttribute("collaborations", collaborationRequests);
        return "collaborations";
    }
    
    @GetMapping("/donate")
    public String showDonationPage(Model model) {
        model.addAttribute("pageTitle", "Make a Donation");
        return "donate";
    }
    
    @PostMapping("/donate/process")
    public String processDonation(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String amount,
            @RequestParam(required = false) String anonymous,
            @RequestParam(required = false) String message,
            Model model) {
        
        // Simple validation
        if (name == null || name.trim().isEmpty() || 
            email == null || email.trim().isEmpty() ||
            amount == null || amount.trim().isEmpty()) {
            model.addAttribute("errorMessage", "Please fill in all required fields.");
            return "donate";
        }
        
        try {
            // In a real app, process the donation payment here
            double donationAmount = Double.parseDouble(amount);
            if (donationAmount <= 0) {
                throw new NumberFormatException();
            }
            
            // In a real app, you would process the payment and save the donation
            model.addAttribute("successMessage", 
                String.format("Thank you for your generous donation of $%.2f! %s", 
                donationAmount,
                "Your support helps students in need."));
                
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "Please enter a valid donation amount.");
            return "donate";
        }
        
        return "donate";
    }
    
    @GetMapping("/financial-aid")
    public String showFinancialAidForm(Model model) {
        model.addAttribute("pageTitle", "Financial Aid Application");
        return "financial-aid";
    }
    
    @PostMapping("/financial-aid/apply")
    public String submitFinancialAid(
            @RequestParam String name, 
            @RequestParam String studentId,
            @RequestParam String email,
            @RequestParam String program,
            @RequestParam String reason,
            Model model) {
        // Simple validation
        if (name == null || name.trim().isEmpty() || 
            studentId == null || studentId.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            program == null || program.trim().isEmpty() ||
            reason == null || reason.trim().isEmpty()) {
            model.addAttribute("errorMessage", "All fields are required.");
            return "financial-aid";
        }
        
        // In a real app, save to database here
        model.addAttribute("successMessage", "Your financial aid application has been submitted successfully!");
        return "financial-aid";
    }

    @PostMapping("/assess")
    public String assess(@ModelAttribute MentalHealthAssessment assessment, Model model) {
        List<String> advice = new ArrayList<>();

        // Generate advice based on assessment
        if (assessment.getFeeling() != null) {
            String feeling = assessment.getFeeling().toLowerCase();
            if (feeling.contains("stressed") || feeling.contains("anxious") || assessment.getStressLevel() >= 4) {
                advice.add("🔹 You seem to be under significant pressure. Take deep breaths and short breaks.");
                advice.add("🔹 Try the 4-7-8 breathing technique: Inhale for 4 seconds, hold for 7, exhale for 8.");
                advice.add("🔹 Consider speaking with a counselor if these feelings persist.");
            } else if (feeling.contains("tired") || feeling.contains("exhausted")) {
                advice.add("🔹 Make sure you're getting enough sleep. Aim for 7-9 hours per night.");
                advice.add("🔹 Try to maintain a consistent sleep schedule, even on weekends.");
            } else if (feeling.contains("overwhelmed")) {
                advice.add("🔹 Break tasks into smaller, manageable steps.");
                advice.add("🔹 Use a planner or digital tool to organize your workload.");
            } else if (feeling.contains("sad") || feeling.contains("down")) {
                advice.add("🔹 Remember that it's okay to feel this way sometimes.");
                advice.add("🔹 Reach out to friends, family, or support services if you need to talk.");
            } else if (feeling.contains("angry") || feeling.contains("frustrated")) {
                advice.add("🔹 Take a short break to cool down before responding to situations.");
                advice.add("🔹 Physical activity can help release built-up tension.");
            } else if (feeling.contains("happy") || feeling.contains("good") || feeling.contains("great")) {
                advice.add("🔹 Great to hear you're feeling positive! Keep up the good work.");
                advice.add("🔹 Consider journaling about what's working well for you.");
            }
        }

        if (assessment.isSleepingWell()) {
            advice.add("🔹 Good sleep is essential for mental health. Keep up the healthy habits!");
        } else {
            advice.add("🔹 Poor sleep affects mental well-being. Try to maintain a consistent sleep schedule.");
            advice.add("🔹 Avoid screens before bedtime and create a calming nighttime routine.");
        }

        if (assessment.hasConcentrationIssues()) {
            advice.add("🔹 Break tasks into smaller, manageable parts.");
            advice.add("🔹 Try the Pomodoro Technique: 25 minutes of focused work, 5-minute break.");
        }

        if (!assessment.isSocializing()) {
            advice.add("🔹 Social connection boosts mood. Reach out to a trusted friend or family member.");
            advice.add("🔹 Even a short conversation can make a big difference.");
        }

        if (advice.isEmpty()) {
            advice.add("🔹 You're doing great! Keep up the good work.");
            advice.add("🔹 Remember to take breaks and practice self-care.");
        }

        model.addAttribute("advice", advice);
        return "mental-health";
    }
}
