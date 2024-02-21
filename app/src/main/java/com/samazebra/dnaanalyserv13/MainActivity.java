package com.samazebra.dnaanalyserv13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView percentAdenineTV = findViewById(R.id.percent_adenine);
        TextView percentThymineTV = findViewById(R.id.percent_thymine);
        TextView percentCytosineTV = findViewById(R.id.percent_cytosine);
        TextView percentGuanineTV = findViewById(R.id.percent_guanine);
        TextView complStrandTV = findViewById(R.id.compl_strand);
        TextView mRNATV = findViewById(R.id.mrna_strand);
        TextView tRNATV = findViewById(R.id.trna_strand);

        percentAdenineTV.setVisibility(View.INVISIBLE);
        percentThymineTV.setVisibility(View.INVISIBLE);
        percentCytosineTV.setVisibility(View.INVISIBLE);
        percentGuanineTV.setVisibility(View.INVISIBLE);
        complStrandTV.setVisibility(View.INVISIBLE);
        mRNATV.setVisibility(View.INVISIBLE);
        tRNATV.setVisibility(View.INVISIBLE);
    }

    public void analyse (View view)
    {
        System.out.println("HERE");

        EditText input = findViewById(R.id.input);

        TextView percentAdenineTV = findViewById(R.id.percent_adenine);
        TextView percentThymineTV = findViewById(R.id.percent_thymine);
        TextView percentCytosineTV = findViewById(R.id.percent_cytosine);
        TextView percentGuanineTV = findViewById(R.id.percent_guanine);

        TextView complStrandTV = findViewById(R.id.compl_strand);
        TextView mRNATV = findViewById(R.id.mrna_strand);
        TextView tRNATV = findViewById(R.id.trna_strand);


        String inputDNA = input.getText().toString().toUpperCase();

        int percentAdenine = findPercent('A', inputDNA);
        int percentThymine = findPercent('T', inputDNA);
        int percentCytosine = findPercent('C', inputDNA);
        int percentGuanine = findPercent('G', inputDNA);

        String complStrand = findComplStrand(inputDNA);
        String mRNA = findMRNA(inputDNA);
        String tRNA = findTRNA(inputDNA);


        percentAdenineTV.setText("Percent of Adenine: " + percentAdenine + "%");
        percentThymineTV.setText("Percent of Thymine: " + percentThymine+ "%");
        percentCytosineTV.setText("Percent of Cytosine: " + percentCytosine+ "%");
        percentGuanineTV.setText("Percent of Guanine: " + percentGuanine+ "%");

        complStrandTV.setText("Complementary Strand: " + complStrand);
        mRNATV.setText("mRNA Strand: " + mRNA);
        tRNATV.setText("tRNA Strand: " + tRNA);

        percentAdenineTV.setVisibility(View.VISIBLE);
        percentThymineTV.setVisibility(View.VISIBLE);
        percentCytosineTV.setVisibility(View.VISIBLE);
        percentGuanineTV.setVisibility(View.VISIBLE);
        complStrandTV.setVisibility(View.VISIBLE);
        mRNATV.setVisibility(View.VISIBLE);
        tRNATV.setVisibility(View.VISIBLE);
    }

    public int findPercent (char letter, String dna)
    {
        int noChar = 0;
        int dnaLength = dna.length();

        for (int i = 0; i < dna.length(); i++)
        {
            char currChar = dna.charAt(i);

            if (currChar == letter)
            {
                noChar++;
            }
        }

        System.out.println(noChar);
        System.out.println(dnaLength);

        int percentChar = (int) Math.round(((double) noChar/ (double) dnaLength)*100);

        return percentChar;
    }

    public String findComplStrand (String dna)
    {
        String complStrand = "";

        for (int i = 0; i < dna.length(); i++)
        {
            char currChar = dna.charAt(i);

            if (currChar == 'A')
            {
                complStrand = complStrand + 'T';
            }
            else if (currChar == 'T')
            {
                complStrand = complStrand + 'A';
            }
            else if (currChar == 'C')
            {
                complStrand = complStrand + 'G';
            }
            else if (currChar == 'G')
            {
                complStrand = complStrand + 'C';
            }
        }

        return complStrand;
    }

    public String findMRNA (String dna)
    {
        String mRNA = "";
        String complStrand = findComplStrand(dna.toUpperCase());

        for (int i = 0; i < complStrand.length(); i++)
        {
            char currChar = complStrand.charAt(i);

            if (currChar == 'T')
            {
                mRNA = mRNA + 'U';
            }
            else
            {
                mRNA = mRNA + currChar;
            }
        }

        return mRNA;
    }

    public String findTRNA (String dna)
    {
        String tRNA = "";

        for (int i = 0; i < dna.length(); i++)
        {
            char currChar = dna.toUpperCase().charAt(i);

            if (currChar == 'T')
            {
                tRNA = tRNA + 'U';
            }
            else
            {
                tRNA = tRNA + currChar;
            }
        }

        return tRNA;
    }
}