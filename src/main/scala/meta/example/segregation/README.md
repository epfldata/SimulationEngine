In this example, we simulate Schelling's segregation model which studies how individual bias can amount to unexpected segregation at the macro level. We model two groups of people, each with view 0 or view 1 respectively. Each one has a tolerance threshold of 50%, namely, if there are at least 50% of neighbors who share the same view, then he or she is happy. Otherwise, the person is unhappy and attempts to move. 

The sample setup is for 50x50 torus world, 90% density (2300 people), and neighborhood of size 8 and radius 1, to be comparable with the setup for NetLogo, available here: https://www.netlogoweb.org/launch#https://www.netlogoweb.org/assets/modelslib/Sample%20Models/Social%20Science/Segregation.nlogo 

At equilibrium, the percent of similar population is around 85%, which is similar to the result achieved with NetLogo (~86%). Check for yourself! 
  
Expected output: 
```
Monitor is enabled: true
(Time 0.0 Turn 0)
(Time 0.0 Turn 1)
(Time 0.0 Turn 2)
(Time 0.0 Turn 3)
(Time 0.0 Turn 4)
(Time 0.0 Turn 5)
0.5445719562063038
(Time 0.0 Turn 6)
(Time 1.0 Turn 7)
(Time 1.0 Turn 8)
(Time 1.0 Turn 9)
0.5445719562063038
...
(Time 6.0 Turn 41)
0.8225909703504071
(Time 6.0 Turn 42)
(Time 7.0 Turn 43)
(Time 7.0 Turn 44)
(Time 7.0 Turn 45)
0.8225909703504071
(Time 7.0 Turn 46)
0.8225909703504071
(Time 7.0 Turn 47)
0.8360390070922024
...
```
