package eu.tutorial.cgpacalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.cgpacalculator.ui.theme.CGPACalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CGPACalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CGPA()
                }
            }
        }
    }
}

@Composable
fun CGPA() {
    var grade1 by remember { mutableStateOf("") }
    var credit1 by remember { mutableStateOf<Int?>(null) }
    var grade2 by remember { mutableStateOf("") }
    var credit2 by remember { mutableStateOf<Int?>(null) }
    var grade3 by remember { mutableStateOf("") }
    var credit3 by remember { mutableStateOf<Int?>(null) }
    var grade4 by remember { mutableStateOf("") }
    var credit4 by remember { mutableStateOf<Int?>(null) }

    var allTimeCGPA by remember { mutableStateOf("0.00") }
    var previousSemesterGrade by remember { mutableStateOf("") }
    var previousSemesterCredit by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        Text(
            "CGPA Calculator \n Aapka anka, aapaka bhavisya",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                color = Color(0xFF000000)
            )
        )
        Spacer8dp()

        SubjectText(subject = "Subject 1")
        GradeTextField(grade = grade1, onValueChange = { grade1 = it })
        Spacer8dp()
        CreditTextField(credit = credit1, onValueChange = { credit1 = it })
        Spacer8dp()

        SubjectText(subject = "Subject 2")
        GradeTextField(grade = grade2, onValueChange = { grade2 = it })
        Spacer8dp()
        CreditTextField(credit = credit2, onValueChange = { credit2 = it })
        Spacer8dp()

        SubjectText(subject = "Subject 3")
        GradeTextField(grade = grade3, onValueChange = { grade3 = it })
        Spacer8dp()
        CreditTextField(credit = credit3, onValueChange = { credit3 = it })
        Spacer8dp()

        SubjectText(subject = "Subject 4")
        GradeTextField(grade = grade4, onValueChange = { grade4 = it })
        Spacer8dp()
        CreditTextField(credit = credit4, onValueChange = { credit4 = it })
        Spacer8dp()

        Row {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        // Calculate CGPA logic here
                        val grades = listOf(grade1, grade2, grade3, grade4)
                        val credits = listOf(credit1, credit2, credit3, credit4)
                        allTimeCGPA = calculateCGPA(grades, credits)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBEABE0)),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        text = "Calculate CGPA",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                }
                Spacer8dp()
                Surface(
                    modifier = Modifier.width(175.dp).wrapContentHeight(),
                    color = Color(0xFF263238),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Your All time\nCGPA : $allTimeCGPA",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 16.sp,
                            color = Color(0xFFFFFFFF)
                        )
                    )
                }
            }
            Surface(
                modifier = Modifier.fillMaxSize().padding(start = 10.dp),
                color = Color(0xFF263238),
                shape = RoundedCornerShape(15.dp)
            ) {
                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Previous semester",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 16.sp,
                            color = Color(0xFFFFFFFF)
                        )
                    )
                    Text(
                        text = "Gread: $previousSemesterGrade, credit: $previousSemesterCredit",
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

fun calculateCGPA(grades: List<String>, credits: List<Int?>): String {
    // Implement your CGPA calculation logic here
    // Example (replace with your actual logic):
    var totalPoints = 0.0
    var totalCredits = 0

    grades.zip(credits).forEach { (grade, credit) ->
        val gradePoints = when (grade.uppercase()) {
            "A+" -> 4.0
            "A" -> 4.0
            "A-" -> 3.7
            "B+" -> 3.3
            "B" -> 3.0
            "B-" -> 2.7
            "C+" -> 2.3
            "C" -> 2.0
            "C-" -> 1.7
            "D+" -> 1.3
            "D" -> 1.0
            "F" -> 0.0
            else -> 0.0 // Handle invalid grade
        }

        if (credit != null) {
            totalPoints += gradePoints * credit
            totalCredits += credit
        }
    }

    return if (totalCredits > 0) {
        String.format("%.2f", totalPoints / totalCredits)
    } else {
        "0.00"
    }
}

@Composable
fun Spacer8dp() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun SubjectText(subject: String) {
    Text(
        text = subject,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            color = Color(0xFF000000)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeTextField(grade: String, onValueChange: (String) -> Unit) {
    TextField(
        value = grade,
        onValueChange = { text -> onValueChange(text) },
        modifier = Modifier.fillMaxWidth().height(47.dp),
        label = { Text("Enter Grade", color = Color.White, fontSize = 12.sp) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color(0xFF7E57C2)
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.White)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditTextField(credit: Int?, onValueChange: (Int?) -> Unit) {
    TextField(
        value = credit?.toString() ?: "",
        onValueChange = { text -> onValueChange(text.toIntOrNull()) },
        modifier = Modifier.fillMaxWidth().height(47.dp),
        label = {
            Text(
                "Enter Credit",
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 12.sp
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color(0xFF7D8CCE)
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.Black)
    )
}

@Preview(showBackground = true)
@Composable
fun CGPAPreview() {
    CGPACalculatorTheme {
        CGPA()
    }
}