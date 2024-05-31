package valentinood.javaweb.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import valentinood.javaweb.domain.Cart;
import valentinood.javaweb.domain.Transaction;
import valentinood.javaweb.domain.WebUser;
import valentinood.javaweb.service.LogService;
import valentinood.javaweb.service.PaypalService;
import valentinood.javaweb.service.TransactionService;
import valentinood.javaweb.service.WebUserService;

import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/payment")
public class PaymentController {
    private final Cart cart;
    private PaypalService paypalService;
    private TransactionService transactionService;
    private WebUserService webUserService;
    private LogService logService;

    @GetMapping("/paypal")
    public String paypal(HttpServletRequest request) {
        try {
            if (cart.getItems().isEmpty()) {
                logService.log(request, "Attempted to access /payment/paypal with an empty cart!");
                return "redirect:/";
            }

            String cancelUrl="http://localhost:8080/payment/paypal/cancel";
            String successUrl="http://localhost:8080/payment/paypal/success";
            Payment payment = paypalService.create(
                    cart.getTotalPrice(),
                    "EUR",
                    "paypal",
                    "sale",
                    cart.getDescription(),
                    cancelUrl,
                    successUrl);

            for(Links links: payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/cash")
    public String cash(HttpServletRequest request) {
        if (cart.getItems().isEmpty()) {
            logService.log(request, "Attempted to access /payment/cash with an empty cart!");
            return "redirect:/";
        }

        return "redirect:/payment/cash/success";
    }

    @GetMapping("/paypal/success")
    public String success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Model model, HttpServletRequest request) {
        try {
            if (cart.getItems().isEmpty()) {
                logService.log(request, "Used attempted to access /payment/paypal/success directly with an empty cart!");
                return "redirect:/payment/error";
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<WebUser> user = webUserService.getUserByUsername(authentication.getName());

            if (user.isEmpty()) {
                logService.log(request, "Failed payment #" + paymentId + " : user not found");
                return "redirect:/payment/error";
            }

            Payment payment = paypalService.execute(paymentId, payerId);

            if(!payment.getState().equals("approved")){
                logService.log(request, "Failed payment #" + paymentId + " for user " + user.get().getUsername() + " : getState() is not approved");
                return "redirect:/payment/error";
            }

            Transaction transaction = transactionService.commit(user.get(), cart, "paypal");

            logService.log(request, "Successful payment #" + paymentId + " for user " + user.get().getUsername());

            model.addAttribute("orderNum", transaction.getId());
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return "payment";
    }

    @GetMapping("/cash/success")
    public String successCash(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<WebUser> user = webUserService.getUserByUsername(authentication.getName());

        if (cart.getItems().isEmpty()) {
            logService.log(request, "Used attempted to access /payment/cash/success directly with an empty cart!");
            return "redirect:/payment/error";
        }

        Transaction transaction = transactionService.commit(user.get(), cart, "cash");

        model.addAttribute("orderNum", transaction.getId());

        return "payment";
    }

    @GetMapping("/error")
    public String error() {
        System.out.println("error");
        return "redirect:/";
    }

    @GetMapping("/cancel")
    public String cancel() {
        System.out.println("cancelled");
        return "redirect:/";
    }
}
