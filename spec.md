
# Halaman Dashboard

## /api/statistic

```json
{
  "totalCreatedEvent": 1000,
  "totalActiveUser": 10000,
  "totalCommitmentReward": 10000
}
```

## /api/event?state=ON_SALE (all)
```json
[
  {
    "eventId": 0,
    "title": "DeFi Fundamental Mastercllass",
    "description": "Learn the basics of decentralized finance, liquidity pools, and yield farming in this comprehensive 3-day workshop.",
    "imageUri": "https://images.unsplash.com/photo-1605810230434-7631ac76ec81?w=400&h=300&fit=crop",
    "priceAmount": 60,
    "commitmentAmount": 50,
    "totalAmount": 110,
    "startSaleDate": 1751295986,
    "startSaleDateHumanReadable": "10 Dec 2024",
    "endSaleDate": 1751299986,
    "endSaleDateHumanReadable": "10 Dec 20224",
    "organizer": "string",
    "location": "string",
    "participant": 10,
    "maxParticipant": 20,
    "status": "ONGOING"
  }
]
```

## /api/event/{eventId}/{participantAddress} (participant view)

```json
  {
  "eventId": 0,
  "title": "DeFi Fundamental Mastercllass",
  "description": "Learn the basics of decentralized finance, liquidity pools, and yield farming in this comprehensive 3-day workshop.",
  "imageUri": "https://images.unsplash.com/photo-1605810230434-7631ac76ec81?w=400&h=300&fit=crop",
  "priceAmount": 60,
  "commitmentAmount": 50,
  "totalAmount": 110, //sum from 2 above
  "startSaleDate": 1751295986,
  "endSaleDate": 1751299986,
  "organizer": "string",
  "location": "string",
  "participant": 10, //A2
  "maxParticipant": 20,
  "status": "ONGOING", //calculate in backend
  "session": [
    {
      "sessionNumber": 0,
      "title": "DeFi Fundamental Mastercllass",
      "startSession": 1751295986,
      "endSession": 1751295986,
      "durationInHours": 1, //calculate in backend
      "durationInMinute": 60, //calculate in backend
      "peopleAttend": 0, // A3
      "status": "UPCOMING|RUNNING|COMPLETE|UNATENDED" // is participant attend
    }
  ],
  "statistic": {
    "sessionAttend": 1, //A1
    "sessionLength": 5, // size of this.session
    "availableCommitment": 10 //commFee - (commFeeRate * event yg sudah selesai)
  }
}
```

## /api/event/{eventId} (ForEO)
```json
  {
  "eventId": 0,
  "title": "DeFi Fundamental Mastercllass",
  "description": "Learn the basics of decentralized finance, liquidity pools, and yield farming in this comprehensive 3-day workshop.",
  "imageUri": "https://images.unsplash.com/photo-1605810230434-7631ac76ec81?w=400&h=300&fit=crop",
  "priceAmount": 60,
  "commitmentAmount": 50,
  "totalAmount": 110,
  "startSaleDate": 1751295986,
  "endSaleDate": 1751299986,
  "organizer": "string",
  "location": "string",
  "participant": 10,
  "maxParticipant": 20,
  "status": "ONGOING",
  "canWithdraw" : false,
  "session": [
    {
      "sessionNumber": 0,
      "title": "DeFi Fundamental Mastercllass",
      "startSession": 1751295986,
      "endSession": 1751295986,
      "durationInHours": 1,
      "durationInMinute": 60,
      "peopleAttend": 0,
      "status": "UPCOMING|RUNNING|COMPLETE|UNATENDED",
      "activeQrButton": true
    }
  ],
  "statistic": {
    "totalRevenue": 1, // A5
    "sessionCompleted": 1, // A4
    "sessionLength": 5,
    "avgAttendanceRatePercent": 10 // A6
  }
}
```


## /api/dashboard/{address}/participant
```json
{
  "statistic": {
    "totalDeposit": 0,
    "totalCommitmentFeeAvailable": 0,
    "totalCommitmentFeeClaimed": 0
  },
  "upcomingSession": [ // A7
    {
      "eventId": 0,
      "eventTitle": "DeFi Fundamentals",
      "sessionNumber": 0,
      "sessionTitle": "Introduction to DeFi",
      "startSession": 1751295986,
      "endSession": 1751295986,
      "durationInHours": 1,
      "durationInMinute": 60
    }
  ],
  "completedSession": [ // A8
    {
      "eventId": 0,
      "eventTitle": "DeFi Fundamentals",
      "sessionNumber": 0,
      "sessionTitle": "Introduction to DeFi",
      "startSession": 1751295986,
      "endSession": 1751295986,
      "isAttended": true
    }
  ]
}
```

## /api/dashboard/{address}/organizer
```json
{
  "statistic": {
    "totalRevenue": 0,
    "availableWithdraw": 0,
    "totalCommitmentFeeClaimed": 0
  },
  "upcomingSession": [
    {
      "eventId": 0,
      "eventTitle": "DeFi Fundamentals",
      "sessionNumber": 0,
      "sessionTitle": "Introduction to DeFi",
      "startSession": 1751295986,
      "endSession": 1751295986,
      "durationInHours": 1,
      "durationInMinute": 60
    }
  ],
  "completedSession": [
    {
      "eventId": 0,
      "eventTitle": "DeFi Fundamentals",
      "sessionNumber": 0,
      "sessionTitle": "Introduction to DeFi",
      "startSession": 1751295986,
      "endSession": 1751295986,
      "isLinkGenerated": true
    }
  ]
}
```
