package cubahomes.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class JWTTokenVerifier extends OncePerRequestFilter {

	@SuppressWarnings("unchecked")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//		System.out.println(request.getRequestURI());
//		if(request.getRequestURI().equals("/register")) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//		String authorizationHeader = request.getHeader("Authorization");
//
//		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")
//				|| authorizationHeader.length() == 0) {
//
//			return;
//		}
//
//		String token = authorizationHeader.replace("Bearer ", "");
//		try {
//			String key = "supersecuresupersecuresupersecuresupersecure";
//			Jws<Claims> jwtClaims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).build()
//					.parseClaimsJws(token);
//
//			Claims body = jwtClaims.getBody();
//
//			String username = body.getSubject();
//
//			List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
//
//			Set<SimpleGrantedAuthority> grantedAuthorites = authorities.stream()
//					.map(m -> new SimpleGrantedAuthority(m.get("authority")))
//					.collect(Collectors.toSet());
//			
//			Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
//					grantedAuthorites);
//			
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			
//			filterChain.doFilter(request, response);
//		} catch (JwtException e) {
//			throw new IllegalStateException(e);
//		}
	}

}
