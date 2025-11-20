import requests
import os
import json

from twilio.rest import Client

from dotenv import load_dotenv
load_dotenv()

URL = "https://api.box8.in/catalog/v2/outlets/app_brand?lat=18.6010921&lon=73.7641245&ver=51&platform=web&brand_id=15&outlet_id=46&service_type=DEL101&app_id=19&desktop_view=1&origin=eatClub"


def send_message():
    
    # account_sid = os.environ["TWILIO_ACCOUNT_SID"]

    # auth_token = os.environ["TWILIO_AUTH_TOKEN"]

    # client = Client(account_sid, auth_token)


    # message = client.messages.create(

    #     body="CHECK THE APP NOW , SERVICE RESUMED.",

    #     from_="+12525883727",

    #     to="+918839685816",

    # )


    # print(message.body)
    print("Message sent successfully (simulated).")

def check_restaurant_status():
    try:
        response = requests.get(URL)
        response.raise_for_status()   # raise error if not 200
        data = response.text  # API returns JSON as text

        json_data = json.loads(data)
        if "overloaded" in data.lower():
            print(" Restaurant is overloaded. Please try again later. CALL FROM EAT3.PY")
            return "overloaded"
        elif (response.status_code == 404):
            send_message()
            print(" Restaurant is accepting orders. CALL FROM EAT3.PY")
            return "accepting"
        elif (json_data.get("status") == "Not OK"):
            send_message()
            print(" Something is wrong with the restaurant status.")
            return "error"
        else:
            print(" Restaurant is accepting orders.")
            return "accepting"
    except Exception as e:
        print("Error fetching API:", e)
        return "error"


