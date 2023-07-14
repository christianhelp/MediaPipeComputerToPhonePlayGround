import cv2
#from tkinter import *

class Image_Capture:
    def __init__(self,cam_port=0) -> None:
        self.cam_port = cam_port

    def display_camera(self):
        cam = cv2.VideoCapture(self.cam_port)
        while(True):
            result,frame = cam.read()
            cv2.imshow('frame',frame)
            # q button will be quit and take picture
            if cv2.waitKey(1) & 0xFF == ord('p'):
                break
        self.take_image_and_save(cam)
    def take_image_and_save(self,cam):
        result,image = cam.read()
        if result:
            while(True):
                cv2.imshow('storeImage',image) #display the captured image
                if cv2.waitKey(1) & 0xFF == ord('s'): #save on pressing 'y' 
                    cv2.imwrite('images/c1.png',image)
                    cv2.destroyAllWindows()
                    break
        else:
            print('NO IMAGE DETECTED!')
        cam.release()

    
if __name__ == '__main__':
    print('Running!')

    image_cap = Image_Capture()

    image_cap.display_camera()


    