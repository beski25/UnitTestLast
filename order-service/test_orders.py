from fastapi.testclient import TestClient
from main import app

def test_create_order_success(requests_mock):
    requests_mock.get("http://localhost:8080/products/1", json={"id":1, "price":10})
    c = TestClient(app)
    r = c.post("/orders", json={"items":[{"product_id":1,"qty":3}]})
    assert r.status_code==200
    assert r.json()["total_amount"]==30
