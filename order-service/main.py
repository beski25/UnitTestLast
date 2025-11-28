from fastapi import FastAPI, HTTPException
import requests

app = FastAPI()

@app.post("/orders")
def create_order(order: dict):
    total = 0
    for item in order["items"]:
        pid = item["product_id"]
        qty = item["qty"]
        try:
            res = requests.get(f"http://localhost:8080/products/{pid}")
        except:
            raise HTTPException(status_code=503)
        if res.status_code == 404:
            raise HTTPException(status_code=400)
        total += res.json()["price"] * qty
    return {"total_amount": total}
