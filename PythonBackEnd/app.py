from flask import Flask,jsonify,request
from Image_Capture import *
import cv2


app = Flask(__name__)
camera_capture = Image_Capture()

'''
Very very bare bones Flask app for example purposes
'''
@app.route('/')
def Test():
    return 'Hello World! Test'

@app.route('/stream')
def stream():
    return 'This is where stream data will come through'

@app.route('/request_image')
def get_image():
    camera_capture.display_camera()
    

if __name__ == '__main__':
    # If it does not properly run the servers, you need to run the command: export FLASK_RUN_HOST="0.0.0.0"
    # Should be running on the 127. and the 192. and the 0.  
    
    app.run(host='0.0.0.0')
