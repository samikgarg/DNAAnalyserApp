package com.samazebra.dnaanalyse2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DnaAnalyserFragment extends Fragment
{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.dna_analyser, container, false);

        EditText input = v.findViewById(R.id.input);

        TextView percentAdenineTV = v.findViewById(R.id.percent_adenine);
        TextView percentThymineTV = v.findViewById(R.id.percent_thymine);
        TextView percentCytosineTV = v.findViewById(R.id.percent_cytosine);
        TextView percentGuanineTV = v.findViewById(R.id.percent_guanine);

        TextView complStrandTV = v.findViewById(R.id.compl_strand);
        TextView mRNATV = v.findViewById(R.id.mrna_strand);
        TextView tRNATV = v.findViewById(R.id.trna_strand);

        Button btn = v.findViewById(R.id.analyse_button);


        String inputDNA = input.getText().toString();

        int percentAdenine = findPercent('A', inputDNA);
        int percentThymine = findPercent('T', inputDNA);
        int percentCytosine = findPercent('C', inputDNA);
        int percentGuanine = findPercent('G', inputDNA);

        String complStrand = findComplStrand(inputDNA);
        String mRNA = findMRNA(inputDNA);
        String tRNA = findTRNA(inputDNA);


        percentAdenineTV.setText(percentAdenine);
        percentThymineTV.setText(percentThymine);
        percentCytosineTV.setText(percentCytosine);
        percentGuanineTV.setText(percentGuanine);

        complStrandTV.setText(complStrand);
        mRNATV.setText(mRNA);
        tRNATV.setText(tRNA);

        return v;
    }

    public int findPercent (char letter, String dna)
    {
        int noChar = 0;
        int dnaLength = dna.length();

        for (int i = 0; i < dna.length(); i++)
        {
            char currChar = dna.toUpperCase().charAt(i);

            if (currChar == letter)
            {
                noChar++;
            }
        }

        int percentChar = Math.round((noChar/dnaLength)*100);

        return percentChar;
    }

    public String findComplStrand (String dna)
    {
        String complStrand = "";

        for (int i = 0; i < dna.length(); i++)
        {
            char currChar = dna.toUpperCase().charAt(i);

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
                complStrand = complStrand + 'C';
            }
            else if (currChar == 'G')
            {
                complStrand = complStrand + 'G';
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
