# from crypt import methods
import numpy as np
from flask import Flask, request, jsonify, render_template
import pickle
from sklearn.compose import ColumnTransformer
from sklearn.preprocessing import OneHotEncoder
import pandas as pd


model = pickle.load(open('model.pkl', 'rb'))
print('model is loaded')
app = Flask(__name__)
dataset = pd.read_csv('train.csv')

@app.route('/', methods=['GET'])
def index():
    GENDER=float(request.args['GENDER'])
    COMPANY=float(request.args['COMPANY'])
    WFH=float(request.args['WFH'])
    DESIG=float(request.args['DESIG'])
    HOURS=float(request.args['HOURS'])
    TIRED=float(request.args['TIRED'])
    MONTH=float(request.args['MONTH'])
    DAY=float(request.args['DAY'])
   
    # X = dataset.iloc[:, 1:-1].values
    # ct = ColumnTransformer(transformers=[('encoder', OneHotEncoder(), [-2])], remainder='passthrough')
    # X = np.array(ct.fit_transform(X))
    # test=[[FRUITS_VEGGIES,
    #       DAILY_STRESS,
    #       PLACES_VISITED,
    #       CORE_CIRCLE,
    #       SUPPORTING_OTHERS,
    #       SOCIAL_NETWORK,
    #       ACHIEVEMENT,
    #       DONATION,
    #       BMI_RANGE,
    #       TODO_COMPLETED,
    #       FLOW,
    #       DAILY_STEPS,
    #       LIVE_VISION,
    #       SLEEP_HOURS,
    #       LOST_VACATION,
    #       DAILY_SHOUTING,
    #       SUFFICIENT_INCOME,
    #       PERSONAL_AWARDS,
    #       TIME_FOR_PASSION,
    #       WEEKLY_MEDITATION,
    #       AGE,
    #       GENDER]]
    # test=ct.transform(test)
    pred=model.predict(np.array([GENDER,
          COMPANY,
          WFH,
          DESIG,
          HOURS,
          TIRED,
          MONTH,
          DAY]).reshape(1,-1))
    return jsonify(prediction=str(pred))

@app.route('/predict',methods=['POST'])
def predict():
    '''
    For rendering results on HTML GUI
    '''
    int_features = [float(x) for x in request.form.values()]
    final_features = [np.array( int_features)]
    prediction = model.predict(final_features)

    output = prediction[0]

    return render_template('index.html', prediction_text='Burnout Rate is {}'.format(output))

@app.route('/predict_api',methods=['POST'])
def predict_api():
    '''
    For direct API calls trought request
    '''
    data = request.get_json(force=True)
    prediction = model.predict([np.array(list(data.values()))])

    output = prediction[0]
    return jsonify(output)

if __name__ == "__main__":
    app.run(debug=True)