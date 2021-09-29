package cubahomes.security;

//@EnableWebSecurity
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig/* extends WebSecurityConfigurerAdapter */{
//
//	private static final String[] resources = new String[] { "/include/**", "/css/**", "/js/**", "/vendor/**",
//			"/icons/**", "/img/**", "/", "/webjars/**" };
//	private static final String[] urls = new String[] { "/", "/login", "/register", "/home", "/terms" };
//	
//	@Autowired
//	UserDetailsService userDetails;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http
//			.csrf().disable()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
//			.addFilter(new JWTUsernamePasswordAuthenticationFilter(authenticationManager()))
//			.authorizeRequests()
//				.antMatchers(resources).permitAll();
//
//	}
//
//	
// 	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetails).passwordEncoder(encoder());
//	}
//
////	@Override
////	@Bean
////	protected UserDetailsService userDetailsService() {
////		UserDetails steve = userDetails.loadUserByUsername("steve");
////		UserDetails yudy = userDetails.loadUserByUsername("yudy");
////		
////		yudy.getAuthorities().forEach(a -> System.out.println(a.getAuthority()));
////		return new InMemoryUserDetailsManager(steve, yudy);
////	}
//
//	@Bean
//	@Autowired
//	public BCryptPasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public FilterRegistrationBean<JWTTokenVerifier> jwtVerifier() {
//		FilterRegistrationBean<JWTTokenVerifier> filter = new FilterRegistrationBean<>();
//		filter.setFilter(new JWTTokenVerifier());
//		filter.addUrlPatterns("/admin/api/**");
//		filter.setOrder(Ordered.LOWEST_PRECEDENCE -1);
//		return filter;
//	}
}
