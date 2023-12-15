// HttpClient.cpp
#include "HttpClient.h"
#include <curl/curl.h>

size_t HttpClient::writeCallback(void* contents, size_t size, size_t nmemb, std::string* output) {
    size_t total_size = size * nmemb;
    output->append(static_cast<char*>(contents), total_size);
    return total_size;
}

char * HttpClient::sendHttpGet() {
    CURL* curl;
    CURLcode res;

    curl_global_init(CURL_GLOBAL_DEFAULT);

    curl = curl_easy_init();
    if (curl) {
        // Set the URL
        curl_easy_setopt(curl, CURLOPT_URL, googleapisURL);

        // Set the write callback function to handle the response
        std::string response_data;
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, writeCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response_data);

        // Perform the HTTP GET request
        res = curl_easy_perform(curl);

        // Check for errors
        if (res != CURLE_OK) {
            fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
        } else {
            // Display the response
            //std::cout << "HTTP Response:\n" << response_data << std::endl;
            auto return_data = new char[response_data.length() + 1];
            response_data.copy(return_data, response_data.length());
            return_data[response_data.length()] = '\0';
            return return_data;
        }

        // Clean up
        curl_easy_cleanup(curl);
    }

    // Global clean up
    curl_global_cleanup();

    return nullptr;
}

