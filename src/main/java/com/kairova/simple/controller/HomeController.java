package com.kairova.simple.controller;

import com.kairova.simple.model.MentalHealthAssessment;
import com.kairova.simple.model.CareerAdvice;
import com.kairova.simple.model.CollaborationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @PostMapping("/assess")
    public String assess(@ModelAttribute MentalHealthAssessment assessment, Model model) {
        List<String> advice = new ArrayList<>();

        // Generate advice based on assessment
        if (assessment.getFeeling() != null) {
            String feeling = assessment.getFeeling().toLowerCase();
            if (feeling.contains("stressed") || feeling.contains("anxious") || assessment.getStressLevel() >= 4) {
                advice.add("🔹 You seem to be under significant pressure. Take deep breaths and short breaks.");
                advice.add("🔹 Try to identify the main source of your stress. Naming it can reduce its impact.");
            } else if (feeling.contains("happy")) {
                advice.add("🔹 Great to hear you're feeling happy! Keep doing what brings you joy.");
            } else if (feeling.contains("tired")) {
                advice.add("🔹 Fatigue can affect your mood. Prioritize rest and consider light physical activity.");
            }
        }

        if (!assessment.isSleepingWell()) {
            advice.add("🔹 Poor sleep affects mental well-being. Try to maintain a consistent sleep schedule.");
            advice.add("🔹 Avoid screens before bedtime and create a calming nighttime routine.");
        } else {
            advice.add("🔹 Good sleep is essential for mental health. Keep up the healthy habits!");
        }

        if (assessment.hasConcentrationIssues()) {
            advice.add("🔹 Break tasks into smaller, manageable parts.");
            advice.add("🔹 Try the Pomodoro Technique: 25 minutes of focused work, 5-minute break.");
        }

        if (!assessment.isSocializing()) {
            advice.add("🔹 Social connection boosts mood. Reach out to a trusted friend or family member.");
            advice.add("🔹 Even a short conversation can make a big difference.");
        } else {
            advice.add("🔹 Staying connected is important for emotional well-being. Keep nurturing relationships!");
        }

        if (assessment.getStressLevel() >= 4) {
            advice.add("\n❗ Your stress level is high. Consider reaching out to a mental health professional for support.");
        }

        model.addAttribute("advice", advice);
        return "results";
    }
}
