import requests
import time

URL = "https://api.box8.in/catalog/v2/outlets/app_brand?lat=18.6010921&lon=73.7641245&ver=51&platform=web&brand_id=15&outlet_id=46&service_type=DEL101&app_id=19&desktop_view=1&origin=eatClub"

def check_restaurant_status():
    try:
        response = requests.get(URL)
        response.raise_for_status()   # raise error if not 200
        data = response.text  # API returns JSON as text

        if "overloaded" in data.lower():
            print("⚠️ Restaurant is overloaded. Please try again later.")
        else:
            print("✅ Restaurant is accepting orders.")
    except Exception as e:
        print("Error fetching API:", e)

# Run once
check_restaurant_status()

# Optional: Poll every 30 seconds
while True:
    check_restaurant_status()
    time.sleep(5)




# def send_message: