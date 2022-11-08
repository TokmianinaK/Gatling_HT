package demo_a

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class RecordedSimulation5users extends Simulation {

	//timer values
	val th_time_min = 1
	val th_time_max = 3

	val httpProtocol = http
		.baseUrl("https://challenge.flood.io")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:106.0) Gecko/20100101 Firefox/106.0")

	val headers_0 = Map(
		"Sec-Fetch-Dest" -> "document",
		"Sec-Fetch-Mode" -> "navigate",
		"Sec-Fetch-Site" -> "none",
		"Sec-Fetch-User" -> "?1",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"Origin" -> "https://challenge.flood.io",
		"Sec-Fetch-Dest" -> "document",
		"Sec-Fetch-Mode" -> "navigate",
		"Sec-Fetch-Site" -> "same-origin",
		"Sec-Fetch-User" -> "?1",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_5 = Map(
		"Accept" -> "*/*",
		"If-None-Match" -> """"7eaba04fa65562f24adc9f393eb36c50"""",
		"Sec-Fetch-Dest" -> "empty",
		"Sec-Fetch-Mode" -> "cors",
		"Sec-Fetch-Site" -> "same-origin",
		"X-Requested-With" -> "XMLHttpRequest")


	//opening home page
	val scn = scenario("RecordedSimulation")
		.exec(http("open home page")
			.get("/")
			.check(css("input[name='authenticity_token']", "value").find.saveAs("authenticity_token"))
			.check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id")))
		.pause(th_time_min)
		.exec(http("click on the start button")
			.post("/start")
			.headers(headers_1)
			.formParam("utf8", "✓")
			.formParam("authenticity_token", "fbdRqwtHzTsfHGPe7aPuIgnGTDFc8q7zNHpz/C9FQMg=")
			.formParam("challenger[step_id]", "UHozc1N2QWovZjNvY0lzUFBTTHlnUT09LS1GL1VsanVsYzVzNGdSMm5WTEtsWExnPT0=--7744f2608a96682b18372836b0ccf8e87cb1db15")
			.formParam("challenger[step_number]", "1")
			.formParam("commit", "Start")
			.check(status.is(302)))
		.pause(th_time_min)

		.exec(http("Select age from dropdown")
			.get("/step/2")
			.check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id")))

		//adding manually get quest page
		.exec(http("select age and click on next button after age selection")
			.post("/start")
			.headers(headers_1)
			.formParam("utf8", "✓")
			.formParam("authenticity_token", "fbdRqwtHzTsfHGPe7aPuIgnGTDFc8q7zNHpz/C9FQMg=")
			.formParam("challenger[step_id]", "RzVjZmdXb1FIMWFiMitlaE1QSUUrZz09LS1ib3ozc0Z1eW1DaWpxMmYvckZjVzF3PT0=--58a00b287196bd076f0bb4d8817bb1908ad77369")
			.formParam("challenger[step_number]", "2")
			.formParam("challenger[age]", "33")
			.formParam("commit", "Next"))
		.pause(th_time_min)

		//adding manually get quest page
		.exec(http("Please select and enter the largest order value below ...")
			.get("/step/3")
			.check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id"))
			//max value
			.check(css(".collection_radio_buttons").findAll.transform(list => list.map(_.toInt).max).saveAs("largest_order"))
			//checked checkbox value
			.check(regex(".*order_selected.*value=\"(.*?)\"").find.saveAs("order_selected")))

		.exec(http("select and enter the largest order value, then click next button")
			.post("/start")
			.headers(headers_1)
			.formParam("utf8", "✓")
			.formParam("authenticity_token", "fbdRqwtHzTsfHGPe7aPuIgnGTDFc8q7zNHpz/C9FQMg=")
			.formParam("challenger[step_id]", "MVYyY0pPL29NMnowbkJsbXgyVHVqdz09LS0wZTJidVhYZkdIQjV0NjMvMFdISnFBPT0=--c87d64ec72620056eb970c3ca3de2ece458023b9")
			.formParam("challenger[step_number]", "3")
			.formParam("challenger[largest_order]", "281")
			.formParam("challenger[order_selected]", "eU5ucFR1OFBFcVNXWlh1WnFXbS9Ddz09LS1hYUdaOXdmT1hlbGh1RDNqQnM3ZGxBPT0=--c719e1d2350bc13d0ffb8ea0317ffca41ce4503d")
			.formParam("commit", "Next"))
		.pause(th_time_min)

		.exec(http("entering displayed token")
			.post("/start")
			.headers(headers_1)
			.formParam("utf8", "✓")
			.formParam("authenticity_token", "fbdRqwtHzTsfHGPe7aPuIgnGTDFc8q7zNHpz/C9FQMg=")
			.formParam("challenger[step_id]", "MzBLSE42YmNISnUyTUc1WVFyanphZz09LS1KclJHVmdaNGxrL0hCRy8xd3M2K0pRPT0=--fff2efa45c31296799377be573b0cc05fa019a26")
			.formParam("challenger[step_number]", "4")
			.formParam("challenger[order_9]", "1667852376")
			.formParam("challenger[order_1]", "1667852376")
			.formParam("challenger[order_2]", "1667852376")
			.formParam("challenger[order_10]", "1667852376")
			.formParam("challenger[order_5]", "1667852376")
			.formParam("challenger[order_11]", "1667852376")
			.formParam("challenger[order_11]", "1667852376")
			.formParam("challenger[order_11]", "1667852376")
			.formParam("challenger[order_8]", "1667852376")
			.formParam("challenger[order_11]", "1667852376")
			.formParam("commit", "Next")

			.resources(http("click next button after token entering")
			.get("/code")
			.headers(headers_5))
			.check(css("span.token").find.saveAs("one_time_token"))
			.check(css("input#challenger_step_id", "value").find.saveAs("step_id")))
		.pause(th_time_min)

		.exec(http("click next button after token entering")
			.post("/start")
			.headers(headers_1)
			.formParam("utf8", "✓")
			.formParam("authenticity_token", "fbdRqwtHzTsfHGPe7aPuIgnGTDFc8q7zNHpz/C9FQMg=")
			.formParam("challenger[step_id]", "SXhqU2QzVS9aUkpJdkE2VnBnRjl1QT09LS1WQUNnUjJ1WWdvaXVsOUdkWkhvaWZ3PT0=--918f9b9f3c4e12e326b7873675236693925b79ce")
			.formParam("challenger[step_number]", "5")
			.formParam("challenger[one_time_token]", "3469135756")
			.formParam("commit", "Next"))
		.pause(th_time_min)
		.exec(http("page done")
			.get("/")
			.headers(headers_0)
		.check(css("input#challenger_step_id", "value").find.saveAs("step_id")))
		.pause(th_time_min)

	setUp(scn.inject(atOnceUsers(5))).protocols(httpProtocol)
}