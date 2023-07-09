from flask import Flask,jsonify,request

app = Flask(__name__)
'''
Very very bare bones Flask app for example purposes
'''
@app.route('/')
def Test():
    return 'Hello World! Test'

@app.route('/stream')
def stream():
    return 'This is where stream data will come through'


if __name__ == '__main__':
    # Should be running on the 127. and the 192. and the 0.  
    app.run(host='0.0.0.0')
