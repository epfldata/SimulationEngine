In this example, we simulate Schelling's segregation model which studies how individual bias can amount to unexpected segregation at the macro level. We model two groups of people, each with view 0 or view 1 respectively. Each one has a tolerance threshold of 50%, namely, if there are at least 50% of neighbors who share the same view, then he or she is happy. Otherwise, the person is unhappy and attempts to move. 

The sample setup is for 50x50 torus world, 90% density (2300 people), and neighborhood of size 8 and radius 1, to be comparable with the setup for NetLogo, available here: https://www.netlogoweb.org/launch#https://www.netlogoweb.org/assets/modelslib/Sample%20Models/Social%20Science/Segregation.nlogo 

At equilibrium, the percent of similar population is around 85%, which is similar to the result achieved with NetLogo (~86%). Check for yourself! 
  
Expected output: 
```
Monitor is enabled: true
(TIMER,0)
(TIMER,1)
(TIMER,2)
(TIMER,3)
(TIMER,4)
(TIMER,5)
(TIMER,6)
0.5399263748779687
(TIMER,7)
0.5399263748779687
(TIMER,8)
0.5399263748779687
(TIMER,9)
0.6075297795886037
(TIMER,10)
0.6075297795886037
(TIMER,11)
0.6520187842240264
(TIMER,12)
0.6543772983289137
... 
(TIMER,58)
0.8489374138241133
(TIMER,59)
0.849273929370408
(TIMER,60)
0.8495798906318627
(TIMER,61)
0.8495674170758016
(TIMER,62)
0.8498333532910256
(TIMER,63)
0.8496893975711115
(TIMER,64)
0.8500564277724537
(TIMER,65)
0.8501893371700362
```
