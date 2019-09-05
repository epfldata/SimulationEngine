library(pheatmap)
library(dplyr)

df_in <- read.csv("/home/mousavi/Desktop/EPFL/economic_simulations/target/scala-2.11/Bakery_x.csv")
df_out <- read.csv("/home/mousavi/Desktop/EPFL/economic_simulations/target/scala-2.11/Bakery_y.csv")

var_in <- df_in %>% select(var_capitalMu, var_employeesMu, var_goodwillMu, var_valueProducedMu, var_total_value_destroyedMu)
var_out <- df_out %>% select(var_capitalMu, var_employeesMu, var_goodwillMu, var_valueProducedMu, var_total_value_destroyedMu)

pheatmap(cor(var_in, var_out))
plot(var_in$var_capitalMu, var_out$var_total_value_destroyedMu)
plot(var_in$var_capitalMu, var_out$var_capitalMu)
