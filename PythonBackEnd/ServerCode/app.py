from flask import Flask

app = Flask(__name__)

@app.route('/')
def Test():
    return 'Hello World!'
