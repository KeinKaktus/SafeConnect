    public String apiKey = "API_KEY";

    public boolean isUsingProxy(String ipAddress){
        try {
            URL url = new URL("http://v2.api.iphub.info/ip/" + ipAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Key", apiKey);
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }
                in.close();

                String jsonResponse = response.toString();
                return jsonResponse.contains("\"block\":1");
            }
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§cError while checking VPN Connection: " + e.getMessage());
        }
        return false;
    }