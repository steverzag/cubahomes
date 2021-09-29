package cubahomes.security;

public class JWTUsernamePasswordAuthenticationFilter/* extends UsernamePasswordAuthenticationFilter */{

//	private final static String key = "supersecuresupersecuresupersecuresupersecure";
//	private AuthenticationManager authenticationManager;
//
//	public JWTUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
//		this.authenticationManager = authenticationManager;
//	}
//	
//	
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException {
//		
//		try {
//			UsernamePasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
//					.readValue(request.getInputStream(), UsernamePasswordAuthenticationRequest.class);
//			Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
//					authenticationRequest.getPassword());
//
//			Authentication authenticated = authenticationManager.authenticate(authentication);
//			return authenticated;
//
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Override
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//			Authentication authResult) throws IOException, ServletException {
//
//		String token = Jwts.builder().setSubject(authResult.getName()).claim("authorities", authResult.getAuthorities())
//				.setIssuedAt(new Date()).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(3)))
//				.signWith(Keys.hmacShaKeyFor(key.getBytes())).compact();
//		response.addHeader("Authorization", "Bearer " + token);
//	}
}
