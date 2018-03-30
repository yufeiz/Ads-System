package webserverDemo;
 
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class SearchAds
 */
@WebServlet("/SearchAds")
public class SearchAds extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletConfig config = null;
    private AdsEngine adsEngine = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAds() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
		super.init(this.config);
		ServletContext application = config.getServletContext();
			String adsDataFilePath1 = application.getInitParameter("adsDataFilePath1");
			String adsDataFilePath2 = application.getInitParameter("adsDataFilePath2");
			String budgetDataFilePath = application.getInitParameter("budgetDataFilePath");
			String memcachedServer = application.getInitParameter("memcachedServer");
			String mysqlHost = application.getInitParameter("mysqlHost");
			String mysqlDb = application.getInitParameter("mysqlDB");
			String mysqlUser = application.getInitParameter("mysqlUser");
			String mysqlPass = application.getInitParameter("mysqlPass");
			int memcachedPortal1 = Integer.parseInt(application.getInitParameter("memcachedPortal1"));
			int memcachedPortal2 = Integer.parseInt(application.getInitParameter("memcachedPortal2"));
			this.adsEngine = new AdsEngine(adsDataFilePath1,adsDataFilePath2,budgetDataFilePath,memcachedServer,memcachedPortal1,memcachedPortal2,mysqlHost,mysqlDb,mysqlUser,mysqlPass);
			this.adsEngine.init();
			System.out.println("adsEngine initialized");
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String query = request.getParameter("q");
		String device_id = request.getParameter("did");
		String device_ip = request.getParameter("dip");
		List<Ad> adsCandidates = adsEngine.selectAds(query, device_id, device_ip);
		for(Ad ad : adsCandidates) {
			System.out.print(ad.adId);
		}
		response.setContentType("application/json; charset=UTF-8");
		String res = new Gson().toJson(adsCandidates);
		response.getWriter().print(res);
		response.getWriter().flush();
		
	}

}
