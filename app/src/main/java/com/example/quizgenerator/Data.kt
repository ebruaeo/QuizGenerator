package com.example.quizgenerator

import java.io.Serializable

object Data : Serializable{
     val questionList = mutableListOf(
        Question(1, "A slug's blood is green.", true),
        Question(2, "Approximately one quarter of human bones are in the feet.", true),
        Question(
            3,
            "The total surface area of two human lungs is approximately 70 square metres.",
            true
        ),
        Question(
            4,
            "In West Virginia, USA, if you accidentally hit an animal with your car, you are free to take it home to eat.",
            true
        ),
        Question(5, "The Earth revolves around the Sun.", true),
        Question(6, "Humans have more than 5 senses.", true),
        Question(7, "Water boils at 100 degrees Celsius at sea level.", true),
        Question(8, "The capital of France is Berlin.", false),
        Question(9, "The chemical symbol for gold is Au.", true),
        Question(10, "Lightning never strikes the same place twice.", false),
        Question(11, "The Great Wall of China is visible from space.", false),
        Question(12, "The human body has 206 bones.", true),
        Question(13, "Shakespeare wrote 'The Odyssey'.", false),
        Question(14, "Bananas are berries.", true),
        Question(15, "The Atlantic Ocean is the largest ocean on Earth.", false),
        Question(16, "Mount Everest is the tallest mountain in the world.", true),
        Question(17, "An octopus has three hearts.", true),
        Question(18, "The human nose can detect over 1 trillion different scents.", true),
        Question(19, "Bats are blind.", false),
        Question(20, "Venus is the closest planet to the Sun.", false),
        Question(21, "Humans and dinosaurs lived at the same time.", false),
        Question(
            22,
            "There are more stars in the universe than grains of sand on all the Earth's beaches.",
            true
        ),
        Question(23, "Honey never spoils.", true),
        Question(24, "Dolphins sleep with one eye open.", true),
        Question(25, "A group of crows is called a murder.", true),
        Question(26, "The shortest war in history lasted 38 minutes.", true),
        Question(27, "An ostrich's eye is bigger than its brain.", true),
        Question(28, "A day on Venus is longer than a year on Venus.", true),
        Question(29, "There are 12 months in a year.", true),
        Question(30, "The longest river in the world is the Amazon River.", false),
        Question(31, "The smallest bone in the human body is the stapes bone in the ear.", true),
        Question(32, "There are more than 100 billion neurons in the human brain.", true),
        Question(33, "Goldfish only have a memory span of three seconds.", false),
        Question(34, "The currency of Japan is the Yen.", true),
        Question(35, "Humans share 50% of their DNA with bananas.", true),
        Question(36, "The speed of light is approximately 300,000 kilometers per second.", true),
        Question(37, "A sneeze can travel at speeds of up to 160 kilometers per hour.", true),
        Question(38, "The most common element in the universe is oxygen.", false),
        Question(39, "The capital city of Australia is Sydney.", false),
        Question(40, "Octopuses have blue blood.", true),
        Question(41, "Penguins can fly.", false),
        Question(42, "The moon has its own light source.", false),
        Question(43, "There are seven continents on Earth.", true),
        Question(44, "The Statue of Liberty was a gift from France.", true),
        Question(45, "The Eiffel Tower is located in Berlin.", false),
        Question(46, "Water is made up of two hydrogen atoms and one oxygen atom.", true),
        Question(47, "The human heart has four chambers.", true),
        Question(48, "A group of lions is called a pride.", true),
        Question(49, "The currency of the United Kingdom is the Euro.", false),
        Question(50, "A crocodile cannot stick its tongue out.", true)
    )
     val quizList = mutableListOf(
        Quiz("Quiz 1",questionList.subList(0, 1)),
        Quiz("Quiz 2",questionList.subList(10, 20)),
        Quiz("Quiz 3",questionList.subList(20, 30)),
        Quiz("Quiz 4",questionList.subList(30, 40)),
        Quiz("Quiz 5",questionList.subList(35, 45)),
        Quiz("Quiz 6",questionList.subList(33, 43)),
        Quiz("Quiz 7",questionList.subList(15, 25)),
        Quiz("Quiz 8",questionList.subList(5, 15)),
        Quiz("Quiz 9",questionList.subList(13, 23)),
        Quiz("Quiz 10",questionList.subList(3, 13)),
        Quiz("Quiz 11",questionList.subList(13, 23)),
        Quiz("Quiz 12",questionList.subList(40, questionList.size))
    )
}