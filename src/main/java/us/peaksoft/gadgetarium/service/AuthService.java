package us.peaksoft.gadgetarium.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.AuthenticationRequest;
import us.peaksoft.gadgetarium.dto.AuthenticationResponse;
import us.peaksoft.gadgetarium.entity.Basket;
import us.peaksoft.gadgetarium.entity.User;
import us.peaksoft.gadgetarium.repository.UserRepository;
import us.peaksoft.gadgetarium.dto.RegisterRequest;
import us.peaksoft.gadgetarium.enums.Role;
import us.peaksoft.gadgetarium.security.JwtService;
import java.time.LocalDate;
import us.peaksoft.gadgetarium.entity.Wishlist;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService tokenUtil;

    public AuthenticationResponse view(String token, String message, User user) {
        AuthenticationResponse response = new AuthenticationResponse();
        User user1 = userRepository.findById(user.getId()).get();
        response.setEmail(user1.getEmail());
        response.setPassword(user1.getPassword());
        response.setFirstname(user1.getFirstName());
        response.setLastname(user1.getLastName());
        response.setCreatedDate(user1.getCreatedDate());
        response.setAuthority(user1.getEmail());
        response.setPhoneNumber(user1.getPhoneNumber());
        response.setRole(user1.getRole());
        response.setToken(token);
        response.setMessage(message);

        if (user != null) {
            response.setAuthority(user.getRole().getAuthority());
        }

        return response;
    }

    public User mapToEntity(RegisterRequest request) {
        return User.builder().firstName(request.getFirstName())
                .lastName(request.getLastname()).email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .phoneNumber(request.getPhoneNumber())
                .createdDate(LocalDate.now())
                .build();
    }

    public AuthenticationResponse responseForRegister(User user) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setFirstname(user.getFirstName());
        authenticationResponse.setLastname(user.getLastName());
        authenticationResponse.setEmail(user.getEmail());
        authenticationResponse.setPhoneNumber(user.getPhoneNumber());
        authenticationResponse.setPassword(user.getPassword());
        authenticationResponse.setCreatedDate(user.getCreatedDate());
        return authenticationResponse;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new BadCredentialsException("User with email: "+request.getEmail()+" aready exists.");
        }
        else{
        User user = mapToEntity(request);
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        user.setWishlist(wishlist);
        Basket basket = new Basket();
        basket.setUser(user);
        user.setBasket(basket);
        userRepository.save(user);
        return responseForRegister(user);
    }}

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(token);
        User user = userRepository.findByEmail(token.getName()).orElseThrow();
        return view(tokenUtil.generateToken(user), "successful", user);
    }

}