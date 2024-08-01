package thundergather.thundergatherbe.auth.jwt;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import thundergather.thundergatherbe.auth.jwt.dto.CustomUserDto;

public record JwtUserDetails(CustomUserDto customUserDto) implements UserDetails {

      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(customUserDto.getRoleType()));
      }

      @Override
      public String getPassword() {
            return customUserDto.getPassword();
      }

      @Override
      public String getUsername() {
            return customUserDto.getEmail();
      }

      @Override
      public boolean isAccountNonExpired() {
            return true;
      }

      @Override
      public boolean isAccountNonLocked() {
            return true;
      }

      @Override
      public boolean isCredentialsNonExpired() {
            return true;
      }

      @Override
      public boolean isEnabled() {
            return true;
      }
}
